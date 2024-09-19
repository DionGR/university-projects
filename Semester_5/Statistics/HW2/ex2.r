# calculate φ for z = 1.34
z <- 1.34
phi <- pnorm(z)
message("CASE A: Ha: μ > μ0")
message("z: ", z)
message("Φ(z): ", phi)
message("p-value (1 - Φ(z)): ", 1 - phi)