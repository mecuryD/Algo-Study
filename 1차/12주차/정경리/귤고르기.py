def solution(k, tangerine):
      
    d={}
    for i in tangerine:
        if i in d: d[i]+=1
        if i not in d: d[i]=1  
    arr= list(d.values())
    arr.sort(reverse = True)
    
    tmp=0
    for i in range(len(arr)):
        tmp+=arr[i]
        if(tmp>=k): return i+1
    
    return len(arr)
