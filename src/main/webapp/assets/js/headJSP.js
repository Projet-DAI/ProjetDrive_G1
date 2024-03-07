	console.log("connect to headJSP.js");
	
	function selectOption(value){
		
		document.getElementById("btnMode").innerText = value;
		
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "ModeChercherServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.send("modeChoisi=" + encodeURIComponent(value));
        
	}