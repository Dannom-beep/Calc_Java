import roman



romans = [["I",1],
        ["II",2],
        ["III",3],
        ["IV",4],
        ["V",5],
        ["VI",6],
        ["VII",7],
        ["VIII",8],
        ["IX",9],
        ["X",10]]

def try_divid(a,b):
    try:
        return a // b
    except Exception:
        return "Error"

def try_minus(a,b):
    try: 
        return roman.toRoman(a - b)
    except Exception:
        return "Error"


for num in romans:
    for num_2 in romans:
        print(f'test_try = new Test(\"{num[1]} + {num_2[1]}\",\"{num[1] + num_2[1]}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[0]} + {num_2[0]}\",\"{roman.toRoman(num[1] + num_2[1])}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[1]} - {num_2[1]}\",\"{num[1] - num_2[1]}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[0]} - {num_2[0]}\",\"{try_minus(num[1],num_2[1])}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[1]} / {num_2[1]}\",\"{try_divid(num[1],num_2[1])}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[0]} / {num_2[0]}\",\"{roman.toRoman(num[1] // num_2[1])}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[1]} * {num_2[1]}\",\"{num[1] * num_2[1]}\");')
        print(f'tests.add(test_try);')
        print(f'test_try = new Test(\"{num[0]} * {num_2[0]}\",\"{roman.toRoman(num[1] * num_2[1])}\");')
        print(f'tests.add(test_try);')