res <- (qnorm(0.975)^2) / (4*(0.03)^2)
message("Sample required (people - rounded): ", round(res) + 1)