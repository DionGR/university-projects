# Part A
heads <- 29
tails <- 21 
p <- 0.5
n <- 50
p_hat <- heads / n

z <- qnorm(0.975)
ci <- z * sqrt(p_hat * (1 - p_hat) / n)
message("95% Confidence Interval: [", p_hat - ci, ", ", p_hat + ci, "]")

# Part B
z <-(p_hat - p) / sqrt(p * (1 - p) / n)
message("z: ", z)
pval <- 2 * pnorm(-z)
message("p-value: ", pval)

# Part C
res <- (qnorm(0.975)^2) / (4*(0.01)^2)
message("Number of throws (rounded): ", round(res))






