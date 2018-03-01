var flashvars = {
	f: 'video/animal.mp4',
	c: 0,
	b: 1,
	i: '/images/i.jpg'
};
var params = {
	bgcolor: '#FFF',
	allowFullScreen: true,
	allowScriptAccess: 'always',
	wmode: 'transparent'
};
CKobject.embedSWF('ckplayer/ckplayer.swf', 'a1', 
					'ckplayer_a1', '600', '400', flashvars, params);
function closelights() { //关灯
//	alert(' 本演示不支持开关灯');
}

function openlights() { //开灯
	//alert(' 本演示不支持开关灯');
}
function ckmarqueeadv(){
	return '广告内容'
}
