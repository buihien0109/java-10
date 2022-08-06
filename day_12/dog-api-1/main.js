// 1 số cách gọi API
// 1. Sử dụng dụng fetch API có sẵn của javascript
// 2. Sử dụng axios (package bên thứ 3) -> import
// 3. Sử dụng AJAX (jquery) -> import

// Thao tác
// Bấm Nút -> Gọi API -> Có kết quả -> Hiển thị

const btnRandom = document.getElementById("btn-random");
const imageEl = document.getElementById("image");

// 1. Sử dụng dụng fetch API có sẵn của javascript
// btnRandom.addEventListener("click", async () => {
//     try {
//         let response = await fetch("https://dog.ceo/api/breeds/image/random");
//         console.log(response);

//         let responseJSON = await response.json();
//         console.log(responseJSON);

//         imageEl.src = responseJSON.message;
//     } catch (error) {
//         console.log(error)
//     }
// })

// 2. Sử dụng axios (package bên thứ 3) -> import
btnRandom.addEventListener("click", async () => {
    try {
        let response = await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(response);

        imageEl.src = response.data.message;
    } catch (error) {
        console.log(error)
    }
})