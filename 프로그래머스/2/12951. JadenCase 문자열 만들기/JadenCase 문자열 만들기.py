def solution(s):
    #x가 공백일 경우 슬라이싱 했을 때 인덱스 값이 범위를 벗어났다는 에러가 나므로 공백일 때 공백을 그대로 넣어주어야 함.
    return " ".join(map(lambda x : x[0].upper()+x[1:].lower() if x else x, [i for i in s.split(' ')]))