mean <- 30

t <- qt(0.975, 23)
message("t*: ", t)

ci <- t * sd / sqrt(24)
message("95% Confidence Interval: [", mean - ci, ", ", mean + ci, "]")