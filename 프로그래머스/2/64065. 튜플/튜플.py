def solution(s):
    answer = []
    li = []
    for i in s.split("},"):
        li.append(i.replace("{", "").replace("}", "").split(",")) #split()은 리스트 형태를 반환

    for j in sorted(li, key=lambda x: len(x)):
        for k in j:
            if k not in answer:
                answer.append(k)
    return list(map(int, answer))