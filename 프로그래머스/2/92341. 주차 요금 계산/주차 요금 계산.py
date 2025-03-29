from collections import deque
def solution(fees, records):
    answer = []
    total = 0

    for i in range(len(records)):
        records[i] = records[i].split(" ")

    records = deque(sorted(records, key=lambda x: x[1]))

    while records:
        time, num, state = records.popleft()

        i_hour, i_min = time.split(":")

        if records and records[0][2] == "OUT":
            time, num, state = records.popleft()
            o_hour, o_min = time.split(":")
            total += ((int(o_hour)-int(i_hour))*60 + int(o_min)) - int(i_min)

        else:
            total += ((23-int(i_hour)) * 60 + 59) - int(i_min)

        if records and num == records[0][1]:
            continue
        else:
            if total > fees[0]:
                v, r = divmod(total-fees[0], fees[2])
                if not r:
                    answer.append(fees[1] + v * fees[3])
                else:
                    answer.append(fees[1] + (v+1) * fees[3])
            else:
                answer.append(fees[1])

            total = 0

    return answer