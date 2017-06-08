addLoadEvent(load);
addLoadEvent(initSubmit);
$(".btn").click(function() {
	is_hide();
})
function load() {
	$(".connect p").eq(0).animate({
		"left" : "0%"
	}, 600);
	$(".connect p").eq(1).animate({
		"left" : "0%"
	}, 400);
}

function is_hide() {
	$(".alert").animate({
		"top" : "-40%"
	}, 300)
}
function is_show() {
	$(".alert").show().animate({
		"top" : "45%"
	}, 300)
}

function insertErrorMsg() {
	if (!document.getElementById)
		return false;
	if (!document.getElementById("submit"))
		return false;
	if (!document.createElement)
		return false;
	if (!document.createTextNode)
		return false;

	if (document.getElementById("errorMsg"))
		return false;

	var p = document.createElement("p");
	p.setAttribute("id", "errorMsg");
	var errorMsg = "账号或者密码错误！";
	var text = document.createTextNode(errorMsg);
	p.appendChild(text);

	var submit_button = document.getElementById("submit");
	var parent = submit_button.parentNode;
	// 在submit按钮之前插入错误提示
	parent.insertBefore(p, submit_button);
}

function initSubmit() {
	if (!document.getElementById)
		return false;
	if (!document.getElementById("loginForm"))
		return false;

	var form = document.getElementById("loginForm");
	form.onsubmit = function() {
		if (submitFormWithAjax())
			return false;
		return true;
	}
}

function submitFormWithAjax() {
	if (!document.getElementById)
		return false;
	if (!document.getElementById("username"))
		return false;
	if (!document.getElementById("password"))
		return false;
	var request = getHTTPObject();
	if (!request)
		return false;
	// 获取表单元素的数据
	var dataParts = new Array();
	var username = document.getElementById("username");
	var pass = document.getElementById("password");
	dataParts[0] = username.name + "=" + encodeURIComponent(username.value);
	dataParts[1] = pass.name + "=" + encodeURIComponent(pass.value);
	var data = dataParts.join("&");
	// 发送Ajax请求
	request.open("POST", "Ajax_login", true);
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	// 设置onreadystatechange函数
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200 || request.status == 0) {
				var text = request.responseText;
				if (text == "error")
					insertErrorMsg();
				else
					window.location.href="login";
			}
		}
	};
	//发送请求
	request.send(data);
	return true;
}
