data <- read.csv("AE.csv")

# fivenum(data$Cancer_Cases_per_capita)
# sd(data$Cancer_Cases_per_capita)
# mu1 <- mean(data$Cancer_Cases_per_capita)
# mu1
# fivenum(data$Carbon_Monoxide_kg_per_capita)
# sd(data$Carbon_Monoxide_kg_per_capita)
# mu2 <- mean(data$Carbon_Monoxide_kg_per_capita)
# mu2

out <- boxplot.stats(data$Cancer_Cases_per_capita)$out
boxplot(data$Cancer_Cases_per_capita, ylab = "hwy")
mtext(paste("Outliers: ", paste(out, collapse = ", ")))

#Scatterplot
# plot(y = data$Cancer_Cases_per_capita,
#      x = data$Carbon_Monoxide_kg_per_capita,
#      ylab = "Cancer Cases per Capita",
#      xlab = "Carbon Monoxide Emissions per Capita (in kg)",
#      main = "Carbon Monoxide vs Cancer Cases",)
# abline(lm(data$Cancer_Cases_per_capita ~ data$Carbon_Monoxide_kg_per_capita), col = "red", lwd = 2)
# text(paste("Correlation:", round(cor(data$Carbon_Monoxide_kg_per_capita, data$Cancer_Cases_per_capita), 2)), y = 0.0052, x = 300)
# cor(y = data$Cancer_Cases_per_capita,
#          x = data$Carbon_Monoxide_kg_per_capita,
#          method = "pearson")

# # Barplot of Carbon Monoxide Emissions and year
# barplot(data$Carbon_Monoxide_kg_per_capita, names.arg = data$Year,
#         xlab = "Year", ylab = "Emissions (kg)",
#         main = "Carbon Monoxide Emissions (US) per Capita")

# # Barplot of Cancer Cases and year
# barplot(data$Cancer_Cases_per_capita, names.arg = data$Year,
#         xlab = "Year", ylab = "Cases",
#         main = "Cancer Cases (US) per Capita")

# # Histogram of Carbon Monoxide
# hist(data$Carbon_Monoxide_kg_per_capita,
#      xlab = "Emissions (kg)",
#      main = "Histogram of Carbon Monoxide Emissions per Capita")
# hist(data$Carbon_Monoxide_kg_per_capita,
#      prob = TRUE,
#      xlab = "Emissions (kg)",
#      main = "Carbon Monoxide Emissions (US) per Capita with Density Curve")
# lines(density(data$Carbon_Monoxide_kg_per_capita), col="red", lwd=1)

# # Histogram of Cancer Cases
# hist(data$Cancer_Cases_per_capita,
#      xlab = "Cases",
#      main = "Cancer Cases (US) per Capita")
# hist(data$Cancer_Cases_per_capita,
#      prob = TRUE,
#      xlab = "Cases",
#      main = "Cancer Cases (US) per Capita with Density Curve")
# lines(density(data$Cancer_Cases_per_capita), col="red", lwd=1)