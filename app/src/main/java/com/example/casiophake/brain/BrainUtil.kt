package com.example.casiophake.brain

import java.util.*
import kotlin.math.pow

val PHEPTINH = arrayOf('+', '-', 'x', '/', '^')

fun checkGrammar(input : String): String? {
    var b = StringBuilder(input)
    var i =0
    while (i<b.length){
        if (b[i] !in PHEPTINH) i++ //khong xét ngoại lệ do chỉ check dấu
        else {
            if (i == 0) if (b[i]!='+'&&b[i]!='-') return null
            if (i == b.length-1) return null
            if (DoUuTien(b[i]) < DoUuTien(b[i + 1])) { //Gia su -*, +* --> LOI
                if (b[i+1] !in PHEPTINH) { // gia su +9, *9 --> tiep tuc
                    i++
                    continue
                }
                return null
            }
            if (DoUuTien(b[i]) == DoUuTien(b[i + 1])) {  //Neu -- or ++ thi duoc, con // or ** --> LOI
                if (DoUuTien(b[i]) == 1) { // neu -- hoac ++ --> +, khong thi --> -
                    if (b[i] == b[i + 1]) {
                        b[i] = '+'
                        b.deleteCharAt(i + 1)
                    } else {
                        b[i] = '-'
                        b.deleteCharAt(i + 1)
                    }
                    continue
                } else {
                    return null
                }
            }
            i++  // Neu UuTien(b[i]) > DoUuTien(b[i + 1], nhu *- thi xet tiep
        }
    }
    return b.toString()
}

fun convert (input: String?):Queue<String>?{
    //nếu input != null thì thực thi hàm let với biết it = input
    input?.let {
        var result = LinkedList<String>()
        var stack = Stack<Char>()
        var prev : Char? = null
        for (i in it){  //xét từng phần tử trong xâu input
            if (i == '!')
                if (prev in '0' .. '9' || prev == ')') result.add(i.toString())
                    else return null//néu ! đứng sau 1 số hoặc dấu ) -->hợp lệ. VD: (1+2)!, 9!

            if (i in '0' .. '9'||i == '.') { // nếu trước i là không phải 1 số thì add vào result, ngược lại add vào last của result
                if (prev in '0' .. '9'|| prev=='.') result[result.size-1] = result.last.plus(i)
                else {
                    result.add(i.toString())
                }
            }
            if (i == '(') {
                stack.add(i)
            }
            if (i == ')') { // add pop cua stack vao result cho den khi gap (. Nếu chưa gặp ( mà đã hết stack thì tức là thừa dấu ) -->lỖI
                while (stack.peek()!= '('){
                    if (stack.size==0) {
                        return null
                    }
                    result.add(stack.pop().toString())
                }
                stack.pop() // pop kí tự ( vì hàm trên chưa pop (
            }
            if (i in PHEPTINH ){
                if (i == '+'||i=='-'){
                    if (prev in PHEPTINH||prev == null||prev == '('){ // nếu thoả mãn điều kiện thì tức là nó là dấu âm dương, VD: 9x-3, -9, (-293+3)
                        result.add(i.toString())
                        prev = '0' // trick, xét tiếp tục thì sẽ gặp 1 số, lúc này prev = 0  nên số đó đc add vào ngay sau dấu âm dương. Xem thêm dòng 57
                        continue
                    }
                }
                while (stack.size>0&& DoUuTien(stack.peek())>= DoUuTien(i)){
                    result.add(stack.pop().toString())
                }
                stack.push(i)
            }
            prev = i

        }
        while (stack.size>0){ //nếu xét hết input vào mà stack vẫn còn phép tính chưa pop vào result thì pop nó vào
            if (stack.peek()!='(') result.add(stack.pop().toString()) else stack.pop()
        }
        return  result
    }
    return null
}

fun solve (input: String):String?{
    var stack = Stack<String>()
    var queue = convert(checkGrammar(input))
    //nếu queue khác rỗng thì thực thi hàm let nếu không return null
    queue?.let {
        while (it.size >0){
            var i = it.remove()
            if (i=="!"){ //gặp ! thì Giai thưa(stak.peek). nếu không null thì gắn peek cho giai thừa đó
                GiaiThua(stack.peek())?.let { stack[stack.size-1] = it.toString() } ?: return null
                continue
            }
            if (i[0] in PHEPTINH&&i.length==1) { //tuc i la 1 phep tinh, nếu không phải phép tính thì push vào stack
                if (stack.size<2) { // không đủ 2 toán hạng để thực hiện phép tính
                    return null
                }
                else {
                        var a = stack.pop()
                        var b = stack.pop()
                        stack.push(
                            when (i){
                                "-" -> (b.toFloat() - a.toFloat()).toString()
                                "+" -> (b.toFloat() + a.toFloat()).toString()
                                "x" -> (b.toFloat() * a.toFloat()).toString()
                                "/" -> (b.toFloat() / a.toFloat()).toString()
                                "^" -> (b.toDouble().pow(a.toDouble())).toString()
                                else -> ""
                            }
                        ).also {
                            if (it == "" ) stack.pop() //stack không lấy giá trị rỗng nên phải pop ra
                        }
                    }
                }
            else stack.push(i)
            }

        } ?: return null
    return stack.peek()
    }

fun DoUuTien(a:Char):Int {
    if (a == '(' || a == ')') return 0
    if (a == '+' || a == '-') return 1
    if (a == '^' ) return 3
    if (a == 'x' || a == '/') return 2
    return 100
}

fun GiaiThua( a : String?):Any?{
    if (a != null) {
        var b = 1f
        a.toFloatOrNull()?.let { //nếu a chuyển về Float đc thì thực hiện hàm let, ngược lại return null
            val d = it.toInt()
            if (it.compareTo(d)==0&&it>-1)
                for (i in 1 .. d){
                    b *= i
                }
            return b
        }
            ?: return null
    }
    else return null
}


