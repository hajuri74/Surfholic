var circle_direction = document.getElementsByClassName("circle_con").lastChild;

window.onload = function() {
    var html = "<div>dddd</div>"
    html += "dfe";

    const body = document.getElementById("wrap");
    //body.innerHTML = '<div>dddd</div>';

    //https 요청에 따라 데이터 값 주기
    //서프홀릭 LiveCam페이지 url 피라미터 값 가져오기 str_region = 부산본점(송정)
    //https://www.surfholic.co.kr/surfholic/wave.php?str_region=%EB%B6%80%EC%82%B0%EB%B3%B8%EC%A0%90(%EC%86%A1%EC%A0%95)&region=
    const urlParams = window.location.search;
    //let region = urlParams.get("str_region");
    
    //실측 기상청 API
    getApi();

    //api key
    

    let url = ""
    //국립해양
    fetch(url)
    .then((response)=>response.json())
    .then((data)=> console.log(data));

    // function callJsonApi(url, saveFilePath) {  // Text API 호출 함수
    //     fetch(url)  // fetch를 통해 API 호출
    //       .then(response => response.json())  // 응답을 JSON으로 변환
    //       .then(data => {
    //         console.log(data);  // 데이터 출력
    //         // saveFilePath를 사용하여 데이터를 저장하거나 추가적인 처리를 수행할 수 있습니다.
    //       })
    //       .catch(error => {
    //         console.error('API 호출 중 오류가 발생했습니다:', error);
    //         // 오류 처리를 수행할 수 있습니다.
    //       });
    // }
      
    // 사용 예시 https://apihub.kma.go.kr/api/json
    // const apiUrl = "https://apihub.kma.go.kr/api/typ01/url/kma_buoy.php?tm=202301241200&stn=0&help=1&authKey=dtUNcalsR1KVDXGpbNdSJQ";
    // const savePath = "/path/to/save/file.json";
    // callJsonApi(apiUrl, savePath);

    // function ajax(data) {
    //     var xhr = new XMLHttpRequest();
    //     xhr.addEventListener("load", function() {
    //         console.log(this.responseText);
    //     });
    //     xhr.open("GET", "https://apihub.kma.go.kr/api/typ01/url/kma_buoy.php?tm=202301241200&stn=0&help=1&authKey=dtUNcalsR1KVDXGpbNdSJQ");
    //     xhr.send();
    // }

    console.log(html);
}//end

function getApi() {
    var xhr = new XMLHttpRequest();
    var url = "https://apihub.kma.go.kr/api/typ01/url/kma_buoy.php?tm=202301241200&stn=0&help=1&authKey=dtUNcalsR1KVDXGpbNdSJQ";
    var params = '?' + encodeURIComponent('tm') + '=' + '202301241200&stn';
    params += '&' + encodeURIComponent('stn') + '=' + '0';
    params += '&' + encodeURIComponent('help') + '=' + '1';
    params += '&' + encodeURIComponent('authKey') + '=' + 'dtUNcalsR1KVDXGpbNdSJQ';

    xhr.open("GET", url + params);
    const status = xhr.status;
    xhr.onreadystatechange = function() {
    console.log(res);
        if(status === 0 || (status >= 200 && status < 400)) {
            var res = xhr.responseText;
            console.log(res);
        }
    }
    xhr.send("");
}