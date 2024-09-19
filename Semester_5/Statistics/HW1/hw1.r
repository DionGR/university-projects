data1 <- c(30.3, 31.0, 31.1, 32.1, 32.6, 32.7, 33.4, 33.6, 34.2, 34.5)
qqnorm(data1, main = "Normal Q-Q Plot Data I", xlab = "Theoretical Quantiles", ylab = "Sample Quantiles")
qqline(data1)
mu1 <- mean(data1)
med1 <- median(data1)
sd1 <- sd(data1)
mu1-sd1; mu1+sd1
mu1-2*sd1; mu1+2*sd1
mu1-3*sd1; mu1+3*sd1
hist(data1, prob=TRUE)
lines(density(data1))
abline(v = mu1,                      
       col = "red",
       lwd = 3)
abline(v = med1,                     
       col = "blue",
       lwd = 3)


# data2 <- c(0.0, 0.0, 0.2, 0.8, 1.2, 1.4, 3.2, 4.2, 6.4, 9.0)
# qqnorm(data2, main = "Normal Q-Q Plot Data II", xlab = "Theoretical Quantiles", ylab = "Sample Quantiles")
# qqline(data2)
# mu2 <- mean(data2)
# med2 <- median(data2)
# sd2 <- sd(data2)
# mu2-sd2; mu2+sd2
# mu2-2*sd2; mu2+2*sd2
# mu2-3*sd2; mu2+3*sd2
# hist(data2, prob=TRUE)
# lines(density(data2))
# abline(v = mu2,                      
#        col = "red",
#        lwd = 3)
# abline(v = med2,                     
#        col = "blue",
# lwd = 3)

# data3 <- c(0, 1, 6, 8, 10, 13, 15, 16, 17, 17, 18, 18, 20, 20, 21, 25, 26, 30, 35, 39, 40, 41, 43, 44, 46, 48, 52, 54, 58, 59, 59, 60, 66, 81, 86, 87, 88, 89, 94, 96)
# qqnorm(data3, main = "Normal Q-Q Plot Data III", xlab = "Theoretical Quantiles", ylab = "Sample Quantiles")
# qqline(data3)
# mu3 <- mean(data3)
# med3 <- median(data3)
# sd3 <- sd(data3)
# mu3-sd3; mu3+sd3
# mu3-2*sd3; mu3+2*sd3
# mu3-3*sd3; mu3+3*sd3
# hist(data3, prob=TRUE)
# lines(density(data3))
# abline(v = mu3,                      
#        col = "red",
#        lwd = 3)
# abline(v = med3,                     
#        col = "blue",
#        lwd = 3)

# data4 <- c(17.5, 18, 20, 20, 21, 25, 26, 30, 35, 39, 40, 41, 43, 44, 46, 48, 52, 54, 58, 59)
# qqnorm(data4, main = "Normal Q-Q Plot Data III - 25-75%", xlab = "Theoretical Quantiles", ylab = "Sample Quantiles")
# qqline(data4)
# mu4 <- mean(data4)
# med4 <- median(data4)
# hist(data4, prob=TRUE)
# lines(density(data4))
# abline(v = mu4,                      
#        col = "red",
#        lwd = 3)
# abline(v = med4,                     
#        col = "blue",
#        lwd = 3)