import numpy as np
import matplotlib.pyplot as plt
from tools import read_predicted_boxes, read_ground_truth_boxes


def calculate_iou(prediction_box, gt_box):
    """Calculate intersection over union of single predicted and ground truth box.

    Args:
        prediction_box (np.array of floats): location of predicted object as
            [xmin, ymin, xmax, ymax]
        gt_box (np.array of floats): location of ground truth object as
            [xmin, ymin, xmax, ymax]

        returns:
            float: value of the intersection of union for the two boxes.
    """
    def get_area(box):
        return (box[2] - box[0]) * (box[3] - box[1])
    
    prediction_area = get_area(prediction_box)
    gt_area = get_area(gt_box)

    # Compute intersection
    int_xmin = max(prediction_box[0], gt_box[0])
    int_xmax = min(prediction_box[2], gt_box[2])
    int_ymin = max(prediction_box[1], gt_box[1])
    int_ymax = min(prediction_box[3], gt_box[3])
    
    int_area = max(0, int_xmax - int_xmin) * max(0, int_ymax - int_ymin)
    
    union_area = prediction_area + gt_area - int_area
    iou = int_area / union_area
    
    assert iou >= 0 and iou <= 1
    
    return iou


def calculate_precision(num_tp, num_fp, num_fn):
    """ Calculates the precision for the given parameters.
        Returns 1 if num_tp + num_fp = 0

    Args:
        num_tp (float): number of true positives
        num_fp (float): number of false positives
        num_fn (float): number of false negatives
    Returns:
        float: value of precision
    """
    denom = num_tp + num_fp 
    
    if denom == 0:
        return 1
    
    return num_tp/denom


def calculate_recall(num_tp, num_fp, num_fn):
    """ Calculates the recall for the given parameters.
        Returns 0 if num_tp + num_fn = 0
    Args:
        num_tp (float): number of true positives
        num_fp (float): number of false positives
        num_fn (float): number of false negatives
    Returns:
        float: value of recall
    """
    denom = num_tp + num_fn
    
    if denom == 0:
        return 0
    
    return num_tp/denom


def get_all_box_matches(prediction_boxes, gt_boxes, iou_threshold):
    """Finds all possible matches for the predicted boxes to the ground truth boxes.
        No bounding box can have more than one match.

        Remember: Matching of bounding boxes should be done with decreasing IoU order!

    Args:
        prediction_boxes: (np.array of floats): list of predicted bounding boxes
            shape: [number of predicted boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
        gt_boxes: (np.array of floats): list of bounding boxes ground truth
            objects with shape: [number of ground truth boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
    Returns the matched boxes (in corresponding order):
        prediction_boxes: (np.array of floats): list of predicted bounding boxes
            shape: [number of box matches, 4].
        gt_boxes: (np.array of floats): list of bounding boxes ground truth
            objects with shape: [number of box matches, 4].
            Each row includes [xmin, ymin, xmax, ymax]
    """
    matches = []
    
    for i in range(len(prediction_boxes)):
        for j in range(len(gt_boxes)):
            iou_score = calculate_iou(prediction_boxes[i], gt_boxes[j])
            if iou_score >= iou_threshold:
                matches.append((i, j, iou_score))
    
    matches = sorted(matches, key=lambda x: x[2], reverse=True)
    
    matched_prediction_boxes = []
    matched_gt_boxes = []
    matched_prediction_box_idxs = set()
    matched_gt_box_idxs = set()
    
    for match in matches:
        pred_box_idx, gt_box_idx, _ = match
        if pred_box_idx not in matched_prediction_box_idxs and gt_box_idx not in matched_gt_box_idxs:
            matched_prediction_boxes.append(prediction_boxes[pred_box_idx])
            matched_gt_boxes.append(gt_boxes[gt_box_idx])
            matched_prediction_box_idxs.add(pred_box_idx)
            matched_gt_box_idxs.add(gt_box_idx)
    
    return np.array(matched_prediction_boxes), np.array(matched_gt_boxes)
    
def calculate_individual_image_result(prediction_boxes, gt_boxes, iou_threshold):
    """Given a set of prediction boxes and ground truth boxes,
       calculates true positives, false positives and false negatives
       for a single image.
       NB: prediction_boxes and gt_boxes are not matched!

    Args:
        prediction_boxes: (np.array of floats): list of predicted bounding boxes
            shape: [number of predicted boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
        gt_boxes: (np.array of floats): list of bounding boxes ground truth
            objects with shape: [number of ground truth boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
    Returns:
        dict: containing true positives, false positives, true negatives, false negatives
            {"true_pos": int, "false_pos": int, false_neg": int}
    """

    matched_predicted_boxes, _ = get_all_box_matches(prediction_boxes, gt_boxes, iou_threshold)
 
    num_tp = len(matched_predicted_boxes)
    num_fp = len(prediction_boxes) - num_tp
    num_fn = len(gt_boxes) - num_tp
    
    return {"true_pos": num_tp, "false_pos": num_fp, "false_neg": num_fn}
    

def calculate_precision_recall_all_images(
    all_prediction_boxes, all_gt_boxes, iou_threshold):
    """Given a set of prediction boxes and ground truth boxes for all images,
       calculates recall and precision over all images
       for a single image.
       NB: all_prediction_boxes and all_gt_boxes are not matched!

    Args:
        all_prediction_boxes: (list of np.array of floats): each element in the list
            is a np.array containing all predicted bounding boxes for the given image
            with shape: [number of predicted boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
        all_gt_boxes: (list of np.array of floats): each element in the list
            is a np.array containing all ground truth bounding boxes for the given image
            objects with shape: [number of ground truth boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
    Returns:
        tuple: (precision, recall). Both float.
    """
    
    num_tp, num_fp, num_fn = 0, 0, 0
    
    for i in range(len(all_prediction_boxes)):
        result = calculate_individual_image_result(all_prediction_boxes[i], all_gt_boxes[i], iou_threshold)
        num_tp += result["true_pos"]
        num_fp += result["false_pos"]
        num_fn += result["false_neg"]
        
    precision = calculate_precision(num_tp, num_fp, num_fn)
    recall = calculate_recall(num_tp, num_fp, num_fn)
    
    return precision, recall

def get_precision_recall_curve(
    all_prediction_boxes, all_gt_boxes, confidence_scores, iou_threshold
):
    """Given a set of prediction boxes and ground truth boxes for all images,
       calculates the recall-precision curve over all images.
       for a single image.

       NB: all_prediction_boxes and all_gt_boxes are not matched!

    Args:
        all_prediction_boxes: (list of np.array of floats): each element in the list
            is a np.array containing all predicted bounding boxes for the given image
            with shape: [number of predicted boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
        all_gt_boxes: (list of np.array of floats): each element in the list
            is a np.array containing all ground truth bounding boxes for the given image
            objects with shape: [number of ground truth boxes, 4].
            Each row includes [xmin, ymin, xmax, ymax]
        scores: (list of np.array of floats): each element in the list
            is a np.array containting the confidence score for each of the
            predicted bounding box. Shape: [number of predicted boxes]

            E.g: score[0][1] is the confidence score for a predicted bounding box 1 in image 0.
    Returns:
        precisions, recalls: two np.ndarray with same shape.
    """
    # Instead of going over every possible confidence score threshold to compute the PR
    # curve, we will use an approximation
    confidence_thresholds = np.linspace(0, 1, 500)
    
    precisions = []
    recalls = []
    
    for threshold in confidence_thresholds:
        prediction_boxes = []
        for i in range(len(all_prediction_boxes)):
            prediction_boxes.append(all_prediction_boxes[i][confidence_scores[i] > threshold])
            
        precision, recall = calculate_precision_recall_all_images(prediction_boxes, all_gt_boxes, iou_threshold)
        
        precisions.append(precision)
        recalls.append(recall)
    
    return np.array(precisions), np.array(recalls)


def plot_precision_recall_curve(precisions, recalls):
    """Plots the precision recall curve.
        Save the figure to precision_recall_curve.png:
        'plt.savefig("precision_recall_curve.png")'

    Args:
        precisions: (np.array of floats) length of N
        recalls: (np.array of floats) length of N
    Returns:
        None
    """
    plt.figure(figsize=(20, 20))
    plt.plot(recalls, precisions)
    plt.xlabel("Recall")
    plt.ylabel("Precision")
    plt.xlim([0.8, 1.0])
    plt.ylim([0.8, 1.0])
    plt.savefig("precision_recall_curve.png")


def calculate_mean_average_precision(precisions, recalls):
    """ Given a precision recall curve, calculates the mean average
        precision.

    Args:
        precisions: (np.array of floats) length of N
        recalls: (np.array of floats) length of N
    Returns:
        float: mean average precision
    """
    # Calculate the mean average precision given these recall levels.
    recall_levels = np.linspace(0, 1.0, 11)
    average_precision = 0
    
    # extrapolate the precision for the recall levelss
    for recall_level in recall_levels:
        precision = precisions[recalls >= recall_level]
        if precision.size == 0:
            continue
        
        average_precision += np.max(precision)
        
    average_precision /= len(recall_levels)

    return average_precision


def mean_average_precision(ground_truth_boxes, predicted_boxes):
    """ Calculates the mean average precision over the given dataset
        with IoU threshold of 0.5

    Args:
        ground_truth_boxes: (dict)
        {
            "img_id1": (np.array of float). Shape [number of GT boxes, 4]
        }
        predicted_boxes: (dict)
        {
            "img_id1": {
                "boxes": (np.array of float). Shape: [number of pred boxes, 4],
                "scores": (np.array of float). Shape: [number of pred boxes]
            }
        }
    """
    # DO NOT EDIT THIS CODE
    all_gt_boxes = []
    all_prediction_boxes = []
    confidence_scores = []

    for image_id in ground_truth_boxes.keys():
        pred_boxes = predicted_boxes[image_id]["boxes"]
        scores = predicted_boxes[image_id]["scores"]

        all_gt_boxes.append(ground_truth_boxes[image_id])
        all_prediction_boxes.append(pred_boxes)
        confidence_scores.append(scores)

    precisions, recalls = get_precision_recall_curve(
        all_prediction_boxes, all_gt_boxes, confidence_scores, 0.5)
    plot_precision_recall_curve(precisions, recalls)
    mean_average_precision = calculate_mean_average_precision(precisions, recalls)
    print("Mean average precision: {:.4f}".format(mean_average_precision))


if __name__ == "__main__":
    ground_truth_boxes = read_ground_truth_boxes()
    predicted_boxes = read_predicted_boxes()
    mean_average_precision(ground_truth_boxes, predicted_boxes)
