var map;
var tokyoMarker;
var fujiMarker;
var prMarker;
var nyMarker;
var veniceMarker;
var niagaraMarker;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 35.6762, lng: 139.6503},
    zoom: 4
  });

  tokyoMarker = new google.maps.Marker({
      position: new google.maps.LatLng(35.6762, 139.6503),
      title: "Tokyo"
  });
  tokyoMarker.setMap(map);

  fujiMarker = new google.maps.Marker({
      position: new google.maps.LatLng(35.3606, 138.7274),
      title: "Mt.Fuji"
  });
  fujiMarker.setMap(map);

  prMarker = new google.maps.Marker({
      position: new google.maps.LatLng(18.2208, -66.5901),
      title: "Puerto Rico"
  });
  prMarker.setMap(map);

  nyMarker = new google.maps.Marker({
      position: new google.maps.LatLng(40.7484, -73.9857),
      title: "Empire State Building"
  });
  nyMarker.setMap(map);

  veniceMarker = new google.maps.Marker({
      position: new google.maps.LatLng(33.9850, -118.4695),
      title: "Venice Beach"
  });
  veniceMarker.setMap(map);

  niagaraMarker = new google.maps.Marker({
      position: new google.maps.LatLng(43.0962, -79.0377),
      title: "Niagara Falls"
  });
  niagaraMarker.setMap(map);
}
