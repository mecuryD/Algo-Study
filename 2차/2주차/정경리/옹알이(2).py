def solution(babbling):
    answer = 0
    words = ["aya", "ye", "woo", "ma"]
    for b in babbling:
        for w in words:
            if (w*2) not in b:# 연속되는 발음은 제외
                b = b.replace(w, " ")

        if len(b.strip())==0:
            answer += 1
            continue
    return answer
