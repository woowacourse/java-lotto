/* JS Document */

/******************************

 [Table of Contents]

 1. Vars and Inits
 2. Set Header
 3. Init Search
 4. Init Menu
 5. Init SVG
 6. Init Google Map


 ******************************/

$(document).ready(function () {
    "use strict";

    /*

    1. Vars and Inits

    */

    var header = $('.header');
    var menuActive = false;
    var map;

    setHeader();

    $(window).on('resize', function () {
        setHeader();
    });

    $(document).on('scroll', function () {
        setHeader();
    });

    initSearch();
    initMenu();
    initSvg();
    initGoogleMap();

    /*

    2. Set Header

    */

    function setHeader() {
        if (window.innerWidth < 992) {
            if ($(window).scrollTop() > 100) {
                header.addClass('scrolled');
            } else {
                header.removeClass('scrolled');
            }
        } else {
            if ($(window).scrollTop() > 100) {
                header.addClass('scrolled');
            } else {
                header.removeClass('scrolled');
            }
        }
        if (window.innerWidth > 767 && menuActive) {
            closeMenu();
        }
    }

    /*

    3. Init Search

    */

    function initSearch() {
        if ($('.main_menu_search_button').length) {
            var search = $('.main_menu_search_button');
            var searchInput = $('.main_menu_search_content');
            search.on('click', function () {
                searchInput.toggleClass('active');
            });
        }
    }

    /*

    4. Init Menu

    */

    function initMenu() {
        if ($('.menu').length) {
            var menu = $('.menu');
            var hamb = $('.burger');

            hamb.on('click', function () {
                if (menuActive) {
                    closeMenu();
                } else {
                    openMenu();
                }
            });
        }
    }

    function closeMenu() {
        var menu = $('.menu');
        menu.removeClass('active');
        menuActive = false;
        menu.css('max-height', "0px");
    }

    function openMenu() {
        var menu = $('.menu');
        menu.addClass('active');
        menuActive = true;
        menu.css('max-height', menu.prop('scrollHeight') + "px");
    }

    /*

    5. Init SVG

    */

    function initSvg() {
        jQuery('img.svg').each(function () {
            var $img = jQuery(this);
            var imgID = $img.attr('id');
            var imgClass = $img.attr('class');
            var imgURL = $img.attr('src');

            jQuery.get(imgURL, function (data) {
                // Get the SVG tag, ignore the rest
                var $svg = jQuery(data).find('svg');

                // Add replaced image's ID to the new SVG
                if (typeof imgID !== 'undefined') {
                    $svg = $svg.attr('id', imgID);
                }
                // Add replaced image's classes to the new SVG
                if (typeof imgClass !== 'undefined') {
                    $svg = $svg.attr('class', imgClass + ' replaced-svg');
                }

                // Remove any invalid XML tags as per http://validator.w3.org
                $svg = $svg.removeAttr('xmlns:a');

                // Replace image with new SVG
                $img.replaceWith($svg);
            }, 'xml');
        });
    }

    /*

    6. Init Google Map

    */

    function initGoogleMap() {
        var myLatlng = new google.maps.LatLng(25.525825, -80.360198);
        var mapOptions =
            {
                center: myLatlng,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                draggable: true,
                scrollwheel: false,
                zoomControl: true,
                zoomControlOptions:
                    {
                        position: google.maps.ControlPosition.RIGHT_CENTER
                    },
                mapTypeControl: false,
                scaleControl: false,
                streetViewControl: false,
                rotateControl: false,
                fullscreenControl: true,
                styles: []
            }

        // Initialize a map with options
        map = new google.maps.Map(document.getElementById('map'), mapOptions);

        // Re-center map after window resize
        google.maps.event.addDomListener(window, 'resize', function () {
            setTimeout(function () {
                google.maps.event.trigger(map, "resize");
                map.setCenter(myLatlng);
            }, 1400);
        });
    }
});