/**
 * 
 */

function callAjax() {

	document.getElementById('loading').innerHTML = "Loading Data...";

	httpRequest = new XMLHttpRequest();

	if (!httpRequest) {
		console.log('Unable to create XMLHTTP instance');
		return false;
	}

	httpRequest.onreadystatechange = function() {
	if (this.readyState === 4) {

	document.getElementById('loading').innerHTML = "";

		if (this.status === 200) {
			var string = this.response;
				document.getElementById("loading").innerHTML = "";
				var obj = string

				// call create table and get parameters
				
				var object = obj.students[0];
				var keys = [];
				keys = Object.keys(object);
				//console.log(keys[0].value());
				
				var lenght = obj.students.length;
				CreateTable(lenght,keys );
				
				
				var id;
				var firstName;
				var lastName;
				var postCode;
				var postOffice;
				for(var i = 0; i< obj.students.length; i++) {
					var values = [];
					values = Object.values(obj.students[i]);
					for (var j = 0; j < values.length; j++){
						var data = values[j];
						var cellId = (i+1)*10+(j+1);
						document.getElementById(cellId).innerHTML = data;
					}
				}
				
				
				
				
		}
	}
	};
	httpRequest.open("GET", "studentListJsonService");
	httpRequest.responseType = 'json';
	httpRequest.send();
}

function CreateTable(length,keys) {
    var table = document.createElement ("table");
    for (var t = 0; t < keys.length; t++){
    	console.log(keys[i]);
    }

    var tHead = table.createTHead ();
    var row = tHead.insertRow (-1);
    for (var i = 0; i < keys.length; i++) {
        var cell = row.insertCell (-1);
        cell.innerHTML = keys[i];
    }

    var tBody = document.createElement ("tbody");
    table.appendChild (tBody);
    for (var j = 0; j < length; j++) {
        var row = tBody.insertRow (-1);
        row.setAttribute("id", "row"+j, 0)
        for (var i = 0; i < keys.length; i++) {
            var cell = row.insertCell (-1);
            cell.id = (j+1)*10+(i+1);
            cell.innerHTML = "[" + (j+1) + ", " + (i+1) + "]";
        }
    }


    var container = document.getElementById ("container");
    container.appendChild (table);
	
	
}