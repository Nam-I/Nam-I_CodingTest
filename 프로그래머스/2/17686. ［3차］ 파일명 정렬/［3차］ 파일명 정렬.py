import re
import functools

def comparator(a, b):
    if a[0].lower() != b[0].lower():
        if a[0].lower() > b[0].lower():
            return 1
        else:
            return -1
    else:
        if int(a[1]) > int(b[1]):
            return 1
        elif int(a[1]) == int(b[1]):
            return 0
        else:
            return -1


def solution(files):
    lst = []

    for i in range(len(files)):
        n_location = re.search('[0-9]+', files[i])
        head = files[i][:n_location.start()]
        if (n_location.end() - n_location.start()) > 4:
            num = files[i][n_location.start():n_location.start()+5]
            tail = files[i][n_location.start()+5:]
        else:
            num = files[i][n_location.start():n_location.end()]
            tail = files[i][n_location.end():]

        lst.append([head, num, tail])

    lst = sorted(lst, key=functools.cmp_to_key(comparator))

    return [''.join(f_name) for f_name in lst]
