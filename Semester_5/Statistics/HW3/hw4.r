# n1 <- 19
# p1 <- 0.25
# n2 <- 15
# p2 <- 0.20

#
# x1 <- rbinom(n1, 1, p1)
# x2 <- rbinom(n2, 1, p2)

# 
# t.test(x1, x2, alternative = "greater", conf.level = 0.95)

n <- 19 
m <- 15

prop.test(x=19, n=34, alternative="greater", conf.level=0.95, correct=FALSE)


# chisq.test(data, p=c(0.198, 0.178, 0.176, 0.196, 0.252))

# data <- matrix(c(22, 19, 16, 15, 8, 10, 12, 20, 9, 5), 5, 2)

# colnames(data) <- c("Smarties", "M&Ms")
# rownames(data) <- c("Brown", "Red", "Yellow", "Blue", "Green")
# data <- as.table(data)
# data

# chisq.test(data, correct = FALSE)
# data