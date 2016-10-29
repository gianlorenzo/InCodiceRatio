/**
 * 
 */


function isnum(obj) {

		if (isNaN(obj.value) || parseInt(obj.value) < 0
				|| parseInt(obj.value) > 9999)

		{

			alert('Nel campo si possono immettere solo numeri!');
			obj.value = "";
			obj.focus();
		}

	}