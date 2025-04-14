def modExp(num, power, mod):
    result = 1
    num = num % mod
    while power > 0:
        if power % 2 != 0:
            result = (result * num) % mod
        num = (num**2) % mod
        power = power // 2
    return result
    
def divideSumMod(num, power, mod):
    if num == 1:
        return power % mod
    else:
        result = (num % (mod * (1 - num)) - modExp(num, power + 1, mod * (1 - num))) // (1 - num)
        return int(result)

cases = int(input())
result = ""
for i in range(cases):
    tempInput = input().split(" ")
    a, n, mod = int(tempInput[0]), int(tempInput[1]), int(tempInput[2])
    result += str(divideSumMod(a, n, mod)) + "\n"

print(result)