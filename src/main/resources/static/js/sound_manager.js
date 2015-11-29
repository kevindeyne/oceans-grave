$(function(){
	$("#audio-control").click(function(){
		if($("#audio-control").hasClass("off")){
			ion.sound.stop("uw_loop");
			$("#audio-control").attr("class", "on").text("Turn audio on");
		} else {
			$("#audio-control").attr("class", "off").text("Turn audio off");
			ion.sound.play("uw_loop");
		}
	});
	
	ion.sound({
	    sounds: [
	        {
	            name: "uw_loop",
	            loop: true
	        }
	    ],
	    volume: 1,
	    path: "sfx/",
	    preload: false,
	    multiplay: true
	});
	ion.sound.play("uw_loop");
});