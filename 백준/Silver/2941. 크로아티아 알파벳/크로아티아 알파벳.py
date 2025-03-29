s = input()
croatia = ["c=", "c-", "dz=", "d-", "lj", "s=", "nj", "z="]

for i in croatia:
    s = s.replace(i, "*")

print(len(s))