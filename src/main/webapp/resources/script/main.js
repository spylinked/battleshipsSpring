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

    $(document).on('click','.start',function(){
        $("body").load( 'game/start?p1name='+ $('#p1name').val() +'&p2name='+ $('#p2name').val() +'&p1bot='+ $("#p1bot").prop("checked") +'&p2bot='+ $("#p2bot").prop("checked"));
    });

    $(document).on('click','.takeTurn',function(){
        $("body").load( '/game/takeTurn');
    });
    $(document).on('click','.giveTurn',function(){
        $("body").load( '/game/giveTurn');
    });
    //$(document).on('click',(e) => {
    $(document).on('click','.rival-cell',function(){
        //console.log(e);
        console.log(this);
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

        $( ".rivalfield" ).load( '/game/shoot?x='+ $(this).attr('x') + '&y='+ $(this).attr('y'), function() {
            $.getJSON("game/status", function(data, status){
                console.log(data.status);
                switch(data.status){
                    case 0 :
                        $( ".message" ).text("PROMAH PEREDAVAY HOD!");
                        $('.giveTurn').show();
                        if(data.next == "Bot")
                            $("body").load( '/game/botTurn');
                        //$( ".message" ).after('<form method="get" action="http://localhost:8080/game/giveTurn"><button type="submit">Continue</button></form>')
                        break;
                    case 1 :
                        $( ".message" ).text("POPAL! KRASAVA");
                        break;
                    case 3 :
                        $( ".message" ).text("UBIL! 4TO TVORISH???");
                        break;
                    case 2 :
                        $( ".message" ).text("POBEDA! MALACA!");
                        break;
                    default:
                        alert("wtf");
                        break;
                }
            });
        });
    });
});