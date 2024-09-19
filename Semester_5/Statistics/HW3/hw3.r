data <- read.csv("HW3/ask3.csv")
attach(data)

males <- subset(data, SEX == 'M')
smales <- subset(males, SMOKER == 'Y')
nmales <- subset(males, SMOKER == 'N')

females <- subset(data, SEX == 'F')
sfemales <- subset(females, SMOKER == 'Y')
nfemales <- subset(females, SMOKER == 'N')


message("# Male Smokers: ", nrow(smales))
message("# Male Non-Smokers: ", nrow(nmales))
message("# Female Smokers: ", nrow(sfemales))
message("# Female Non-Smokers: ", nrow(nfemales))

pM <- nrow(smales) / nrow(subset(data, SEX == 'M'))
pF <- nrow(sfemales) / nrow(subset(data, SEX == 'F'))
p <- (nrow(sfemales) + nrow(smales)) / nrow(data)

message("Probability a Male is a smoker: ", pM)
message("Probability a Female is a smoker: ", pF)
message("Probability a Person is a smoker: ", p)

z <- (pF - pM) / sqrt(p * (1 - p) / nrow(females) + p * (1 - p) / nrow(males))
message("z: ", z)

pval <- 2 * pnorm(-z)
message("p-value: ", pval)

c <- (pF - pM) + c(-1,1)*(-1) * qnorm(0.025) * sqrt(pF*(1-pF)/sum(SEX == 'F') + pM*(1- pM)/sum(SEX == 'M')) 
message("95% Confidence Interval: [", c[1], ", ", c[2], "]")

ta <- table(SMOKER, SEX)

addmargins(ta)

chisq.test(ta, correct = FALSE)

