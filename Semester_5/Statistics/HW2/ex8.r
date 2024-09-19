data <- read.csv("survey_data_2022.csv")

attach(data)

# m <- prob[gender == 'M' & prob >= 5]
# f <- prob[gender == 'F' & prob >= 5]
# m <- m[!is.na(m)]
# f <- f[!is.na(f)]

# # boxplot(m ,col="lightblue", main="Difference of Garage and Expert Estimates", xlab="Difference", ylab="Frequency")


# # m <- prob[gender == 'M' & math >= 5]
# # f <- prob[gender == 'F' & math >= 5]

# boxplot(m, col="lightblue", main="Probability Grades for Males", xlab="Males", ylab="Grade")
# boxplot(f, col="pink", main="Probability Grades for Females", xlab="Females", ylab="Grade")

# t.test(m, f, alternative="greater")

# get male heights
# h <- height[gender == 'M' & height < 2 & height > 1.60]
# hf <- height[gender == 'F']

# boxplot(h, col="lightblue", main="Male Heights", xlab="Males", ylab="Height (cm)")
# t.test(h, hf)
# mal <- subset(data, gender == "M")
# male <- subset(mal, prob >= 5.0)
# fem <- subset(data, gender == "F")
# female <- subset(fem, prob >= 5.0)

# remove column math


# t.test(male, female)

# hist(male, col="lightgreen", main="Histogram of Male Probability Grades", xlab="Grade", ylab="Frequency")
# hist(fmale, col="lightgreen", main="Histogram of Female Probability Grades", xlab="Grade", ylab="Frequency")

# t.test(male, fmale, alternative="greater", conf.level=0.95)

math <- data$math
prob <- data$prob
diff <- prob - math
diff <- diff[!is.na(diff)]
diffn <- diff[diff > -5 & diff < 4.9]
length(diff)
length(diffn)

boxplot(diff, col="yellow", main="Grade Difference between Math and Probabilities", xlab="Males", ylab="Grade Difference")
boxplot(diffn, col="purple", main="Grade Difference between Math and Probabilities", xlab="Males", ylab="Grade Difference")

t.test(diffn)

# hist(diff, col="red", main="Histogram of the difference between Math 1 and Prob grades", xlab="Absolute Difference", ylab="Frequency")

# t.test(diff, mu=0, alternative="greater", conf.level=0.95)

