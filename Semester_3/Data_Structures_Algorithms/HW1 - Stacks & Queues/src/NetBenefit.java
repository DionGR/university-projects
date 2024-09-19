import java.io.FileReader;       // FileReader module for reading the file.
import java.io.BufferedReader;   // BufferedReader module for the file stream.

import bundle.Bundle;
import data_structures.IntQueueImpl;

/**
 * PART C: Net Benefit
 *
 * This is a program that calculates the net benefit of a company that trades shares.
 * It utilizes the previously created IntQueueImpl class to store the share prices.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


class NetBenefit {
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));                  // Open the file using the given filename.
            String line;

            IntQueueImpl<Bundle> queue = new IntQueueImpl<Bundle>();                          // Create a new queue.
            int ownedShares = 0;
            int profit = 0;

            if(br.ready()) {
                while((line = br.readLine()) != null){                                         // Read the file line by line.
                    String[] split = line.split(" ");                                    // Split the line into the parts we need.
                    int amountOfShares = Integer.parseInt(split[1]);
                    int price = Integer.parseInt(split[3]);

                    if(amountOfShares <= 0 || price < 0)                                       // If the amount of shares or the price is negative, throw an exception.
                        throw new IllegalArgumentException("Invalid input. Invalid amount of shares or price.");


                    if(split[0].equals("buy")) {                                               // If the line is a buy, add the bundle to the queue.
                        queue.put(new Bundle(amountOfShares, price));
                        ownedShares += amountOfShares;
                    }else{
                        if(amountOfShares > ownedShares)                                       // If the amount of shares is more than the owned shares, throw an exception.
                                throw new IllegalArgumentException("The amount of shares to sell is greater than the amount of shares available.");
                        ownedShares -= amountOfShares;
                        while(amountOfShares > 0) {                                            // While the amount of shares is more than 0, keep selling.
                            if(queue.peek().getShares() <= amountOfShares) {                   // If the amount of shares in the bundle is less than or equal to the amount of
                                Bundle toSell = queue.get();                                   // shares to sell, sell all the shares in the bundle.
                                profit += toSell.getShares() * (price - toSell.getPrice());
                                amountOfShares -= toSell.getShares();
                            }else{                                                             // If the amount of shares in the bundle is more than the amount of shares to sell,
                                profit += amountOfShares * (price - queue.peek().getPrice());  //  sell the amount and deduct the shares sold from the bundle.
                                queue.peek().setShares(queue.peek().getShares() - amountOfShares);
                                break;
                            }
                        }
                    }
                }
            }
            br.close();

            if(profit < 0)       System.out.println("The net loss is: " + -profit);
            else if(profit > 0)  System.out.println("The net profit is: " + profit);
            else                 System.out.println("You have no net profit.");

        } catch (Exception e) {
            if(e instanceof java.io.FileNotFoundException || e instanceof ArrayIndexOutOfBoundsException)
                System.out.println("Incorrect input. The file is not found.");   // If no input is given or the file does not exist, print the error message.
            else
                System.out.println("Error: " + e.getMessage());                  // If there is another error, print the error message.
        }
    }
}