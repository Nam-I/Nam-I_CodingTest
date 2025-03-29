import sys

# 입력에서 첫 번째 줄을 제외한 모든 줄을 읽어옴
name_list = sys.stdin.readlines()[1:]

# 나이를 기준으로 이름을 정렬
# lambda 함수를 사용하여 각 줄에서 나이를 추출하고 int형으로 변환
name_list.sort(key=lambda iD : int(iD.split()[0]))

# 정렬된 이름 리스트를 출력
# 리스트의 각 요소를 이어붙인 문자열을 출력
print("".join(name_list))
