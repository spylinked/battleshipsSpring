$( function() {
    $( ".ship-box" ).draggable({
        snap: ".cell",
        snapMode: "inner",
        stop: function( event, ui ) {
            console.log(event);
            console.log(ui);
            console.log("----");
            console.log(this);
            console.log("----");
            console.log($(this));
            console.log($(this).data('draggable'));
            console.log("----");
            console.log($(this).parent());
            console.log("----");
            console.log($(this).data('ui-draggable'));
            var snapped = $(this).data('ui-draggable').snapElements;
            /* Pull out only the snap targets that are "snapping": */
            var snappedTo = $.map(snapped, function(element) {
                console.log(element);
                return element.snapping ? element.item : null;
            });
            console.log(snappedTo);
        }
    });
} );

$(document).ready(function() {
    $(".rival-cell").click(function(){
/*
        console.log("hello" + $(this).attr('x') + $(this).attr('y'))
        const Http = new XMLHttpRequest();
        const url='/game/shoot?x='+ $(this).attr('x') + '&y='+ $(this).attr('y');
        //const url='/game';
        Http.open("GET", url);
        Http.send();

        Http.onreadystatechange = (e) => {
            console.log(Http.responseText);
            //$(".rivalfield").replaceWith(Http.responseText);
        }

 */
        $( ".rivalfield" ).load( '/game/shoot?x='+ $(this).attr('x') + '&y='+ $(this).attr('y') );
        $.get("status", function(data, status){
            switch(data){
                case "Miss" :
                    $( ".message" ).text("PROMAH PEREDAVAY HOD!");
                    $( ".message" ).after('<form method="get" action="http://localhost:8080/game/giveTurn"><button type="submit">Continue</button></form>')
                    break;
                case "Wound" :
                    $( ".message" ).text("POPAL! KRASAVA");
                    break;
                case "Kill" :
                    $( ".message" ).text("UBIL! 4TO TVORISH???");
                    break;
                case "Win" :
                    $( ".message" ).text("POBEDA! MALACA!");
                    break;
            }
        });
    });
});