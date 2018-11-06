window.onload = function() {
function toJSONString( form ) {
    var obj = {};
    var elements = form.querySelectorAll( "input, select, textarea" );
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if( name ) {
            obj[ name ] = value;
        }
    }
    return JSON.stringify( obj );
}

function sendAjax(method, elem) {
    var request = new XMLHttpRequest();
    request.open(method, '/list', false);
    request.setRequestHeader( 'Content-Type', 'application/json' );
    request.send(toJSONString(elem));
    return request;
}


    var elements = document.querySelectorAll('.js-list-elem');
    for (var i = 0; i < elements.length; ++i)
    {
        elements[i].querySelector('.list-elem-remove').onclick = function (){
            var elem = this.closest('.js-list-elem');
            var id = elem.querySelector('.js-list-elem-id').value;
            var request = sendAjax('Delete', elem);
            if (request.status != 200 || request.response != id) {
                alert("Something is wrong...");
            } else {
                elem.parentNode.removeChild(elem);
            }
        }
        elements[i].querySelector('.list-elem-update').onclick = function (){
                    var elem = this.closest('.js-list-elem');
                    var id = elem.querySelector('.js-list-elem-id').value;
                    var request = sendAjax('Put', elem);
                    if (request.status != 200 || request.response != id) {
                        alert("Something is wrong...");
                    }
                }
    }
}