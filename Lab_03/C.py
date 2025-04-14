def modExp(num, pow):
    if pow == 1:
        return num % 107
    if pow % 2 == 0:
        return (modExp(num, pow / 2) ** 2) % 107
    else:
        return (num * modExp(num, pow - 1)) % 107
    
tempInput = input().split(" ")
num, pow = int(tempInput[0]), int(tempInput[1])
print(modExp(num, pow))