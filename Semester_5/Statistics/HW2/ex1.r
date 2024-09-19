data <- c(82, 55, 58, 94, 86, 45, 42, 36, 41, 130, 96, 39, 107, 52, 54, 45, 81, 83, 38)

sd <- sd(data)
mean <- mean(data)



boxplot(data, col="lightblue", main="Boxplot of Average Query Time (Excl. 284)", xlab="Chosen Day", ylab="Query Time (ms)")

message("Standard Deviation (s): ", sd)
message("Mean (x̄): ", mean)


t <- qt(0.975, 18)
message("t*: ", t)


ci <- t * sd / sqrt(19)
message("95% Confidence Interval: [", mean - ci, ", ", mean + ci, "]")


