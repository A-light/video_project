function createXMLHttpRequest() {
		try {
			return new XMLHttpRequest();//大多数浏览器
		} catch (e) {
			try {
				return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
			} catch (e) {
				try {
					return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
				} catch (e) {
					alert("哥们儿，您用的是什么浏览器啊？");
					throw e;
				}
			}
		}
}
/*
 * option 有如下属性
 */
/*请求方式*method,/
 * /*请求的url,*//*是否异步*asyn,/
/*请求体*params,//*回调方法*callback/
 * /*服务器响应数据类型type*/
function ajax(option){
	var xmlHttp =createXMLHttpRequest();
	if(!option.method){
		option.method="GET";
	}
	if(option.asyn==undefined){
		option.asyn=true; 
	}
	xmlHttp.open(option.method,option.url,option.asyn);
	if(option.method=="POST"){
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	}
	xmlHttp.send(option.params);
	xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
		// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {//双重判断
			var data;
			// 获取服务器的响应数据，进行转换！
			if(!option.type) {//如果type没有赋值，那么默认为文本
				data = xmlHttp.responseText;
			} else if(option.type == "xml") {
				data = xmlHttp.responseXML;
			} else if(option.type == "text") {
				data = xmlHttp.responseText;
			} else if(option.type == "json") {
				var text = xmlHttp.responseText;
				data = eval("(" + text + ")");
			}
			// 调用回调方法
			option.callback(data);
		};
	};
};




