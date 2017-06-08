//添加onload事件
function addLoadEvent(func){
	var oldonload = window.onload;
	if (typeof oldonload != "function"){
		window.onload = func;
	}else{
		window.onload = function(){
			oldonload();
			func();
		}
	}
}

//在标签元素后插入新标签
function insertAfter(newElement , targetElement){
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement){
		parent.appendChild(newElement);
	}else{
		parent.insertBefore(newElement , targetElement.nextSibling);
	}
}

function getHTTPObject(){
	if (typeof XMLHttpRequest == "undefined")
		XMLHttpRequest = function(){
			// return new ActiveXObject("Microsoft.XMLHTTP");
			try{
				return new ActiveXObject("Msxml2.XMLHTTP.6.0");
			}catch(e){}
			try{
				return new ActiveXObject("Msxml2.XMLHTTP.3.0");
			}catch(e){}
			try{
				return new ActiveXObject("Msxml2.XMLHTTP");
			}catch(e){}
			return false;
		}
		return new XMLHttpRequest();
}