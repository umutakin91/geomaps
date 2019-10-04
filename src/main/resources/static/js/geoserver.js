$( document ).ready(function() {
    console.log( "ready!" );

    $(document).on("click",".city-link",function() {
        console.log("click!!");
        var lat = $(this).attr("data-lat");
        var lng = $(this).attr("data-lng");
        var mapUrl = "https://www.google.com/maps/embed/v1/view?key=AIzaSyBXPycqNynAMcPeocv7QuyIrkhj32nxrto&center=" + lat + "," + lng + "&zoom=10"
        $("#resultMap").attr("src", mapUrl);
    });

});