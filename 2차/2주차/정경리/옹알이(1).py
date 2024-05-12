def solution(babbling):
    words = ["aya", "ye", "woo", "ma"]
    answer = 0
    
    for b in babbling:
        for w in words:
            # babbling 중 words에 해당되는게 있으면 공백으로 변경
            b = b.replace(w, " ")
        if len(b.strip())==0:# 모든 공백 제거 후 아무것도 없으면 발음가능
            answer += 1
            continue
    return answer
