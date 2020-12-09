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