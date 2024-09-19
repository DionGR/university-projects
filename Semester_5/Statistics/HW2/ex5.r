# data <- c(71, 65, 67, 54, 55, 83, 73, 82, 69, 70, 59, 80, 81, 75, 83, 73, 77, 91, 92, 86, 73, 68, 72, 72)

# t <- qt(0.975, 23)
# sd <- sd(data)


# ci <- t * sd / sqrt(24)
# mean <- mean(data)

# message("Sample Size: ", length(data))
# message("Mean: ", mean)
# message("Standard Deviation: ", sd)

# message("95% Confidence Interval: [", mean - ci, ", ", mean + ci, "]")

# men <- c(80, 81, 75, 83, 73, 77, 91, 92, 86, 73, 68, 72, 72)
# women <- c(71, 65, 67, 54, 55, 83, 73, 82, 69, 70, 59)

# mmean <- mean(men)
# wmean <- mean(women)
# diff <- mmean - wmean
# msd <- sd(men)
# wsd <- sd(women)


# t <- -qt(0.1, 10)
# ci <- t * sqrt(msd^2 / 13 + wsd^2 / 11)

# message("Men Mean: ", mmean)
# message("Women Mean: ", wmean)
# message("Difference of Means: ", diff)
# message("Men Standard Deviation: ", msd)
# message("Women Standard Deviation: ", wsd)
# message("t*: ", t)
# message("error: ", ci)
# message("80% Confidence Interval: [", diff - ci, ", ", diff + ci, "]")

# smoker <- c(80, 83, 77, 73, 92, 86)
# data_no_smoke <- c(81, 75, 91, 73, 68, 72, 72)

# diff <- mean(smoker) - mean(data_no_smoke)
data = read.csv("survey_data_2022.csv")


f <- data["gender" == "F" & "prob" >= 5.0]
m <- data["gender" == "M" & "prob" >= 5.0]

f 
m


# sds <- sd(smoker)
# nsd <- sd(data_no_smoke)
# a <- (sds^2 )/ 10
# b <- (nsd^2 ) / 14
# z <- diff / sqrt(a + b)
# t.test(smoker, data_no_smoke, alternative="two.sided", conf.level=0.95)


# stem(smoker)
# stem(data_no_smoke)

# smean <- mean(smoker)
# nmean <- mean(data_no_smoke)
# diff <- smean - nmean
# ssd <- sd(smoker)
# nsd <- sd(data_no_smoke)
# a <- (ssd^2 )/ 10
# b <- (nsd^2 ) / 14
# z <- diff / sqrt(a + b)
# p <- 2 * pt(df=9, -abs(z))

# message("Smoker Mean: ", smean)
# message("Non-Smoker Mean: ", nmean)
# message("Difference of Means: ", diff)
# message("Smoker Standard Deviation: ", ssd)
# message("Non-Smoker Standard Deviation: ", nsd)
# message("z: ", z)
# message("p-value: ", p)


# qqnorm(data, main="Normal QQ Plot", xlab="Theoretical Quantiles", ylab="Sample Quantiles")
# qqline(data)



# t.test(smoker, data_no_smoke, alternative="greater", conf.level=0.95)