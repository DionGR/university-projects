g <- c(500, 1550, 1250, 1300, 750, 1000, 1250, 1300, 800, 2500)
e <- c(400, 1500, 1300, 1300, 800, 800, 1000, 1100, 650, 2200)


gmean <- mean(g)
gstd <- sd(g)
emean <- mean(e)
estd <- sd(e)
diff <- gmean - emean



message("Garage Mean: ", gmean)
message("Expert Mean: ", emean)
message("Mean difference (δ): ", diff)


diffs <- g - e
dstd <- sd(diffs)
dmean <- mean(diffs)

# stem(diffs)
boxplot(diffs,col="lightblue", main="Difference of Garage and Expert Estimates", xlab="Difference", ylab="Frequency")


t.test(diffs, mu=0, alternative="greater", conf.level=0.95)