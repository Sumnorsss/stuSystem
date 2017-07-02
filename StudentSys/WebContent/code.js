var code;
// 产生验证码
function createCode() {
	code = "";
	// 验证码的长度
	var codeLength = 4;
	//
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";
	// 定义一个数组存可产生的验证码
	var selectChar = new Array
	(
		1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 'a', 'b', 'c', 'd,', 'e', 'f', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	);
	
	for (var i = 0; i < codeLength; i++) {
		// 取得随机数
		var charIndex = Math.floor(Math.random() * 60);
		// 把每个验证码拼接起来
		code += selectChar[charIndex];
	}
	// 如果长度不为0,则递归调用createCode
	if (code.length != codeLength) {
		createCode();
	}
	// 把code值赋给验证码
	checkCode.value = code;

}

function validate() {
	// 将输入的字母转换为大写处理
	var inputCode = document.getElementById("input1").value.toUpperCase();
	// 将验证码框里面的字母转变成大写
	var codeToUp = code.toUpperCase();
	if (inputCode.length <= 0) {
		alert("请输入验证码");
		return false;
	} else if (inputCode != codeToUp) {
		alert("验证码输入错误");
		return false;
	} else {
		return true;
	}
}