import re
from collections import Counter
def makeDict(s):
    d = {}
    for i in range(len(s)-1):

        lower_s = s[i:i+2].lower()
        if re.match('^[a-zA-Z]+$', lower_s):
            d[lower_s] = d.get(lower_s, 0) + 1

    return d

def solution(str1, str2):
    i = 0
    u = 0
    s1 = makeDict(str1)
    s2 = makeDict(str2)

    if len(s2) < len(s1):
        short_d = s2
        long_d = s1
    else:
        short_d = s1
        long_d = s2

    short_d_k = list(short_d)

    for k in short_d_k:
        if k in long_d:
            i += min(short_d[k], long_d[k])
            u += max(short_d[k], long_d[k])
            del short_d[k]
            del long_d[k]
        else:
            u += short_d[k]
            del short_d[k]


    u += sum(long_d.values())

    if u != 0:
        return int(i/u*65536)
    return 1*65536