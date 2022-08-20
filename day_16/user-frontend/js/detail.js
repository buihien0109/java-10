// const init = async = () => {
//     try {
//         await getUsers();
//         await getProvince();
//     } catch (error) {
//         console.log(error)
//     }
// }

// init();

const API_URL = "http://localhost:8080/api/v1/users";

let id = 1;
let images = [];

// Truy cập
const btnModalImage = document.getElementById("btn-modal-image");
const imageContainerEl = document.querySelector(".image-container");
const btnChoseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");
const modalImageEl = document.getElementById("modal-image");
const avatarEl = document.getElementById("avatar");

btnModalImage.addEventListener("click", async () => {
    try {
        let res = await axios.get(`${API_URL}/${id}/files`);
        console.log(res.data);

        images = res.data;
        renderImage(images);
    } catch (error) {
        console.log(error);
    }
});

const renderImage = (arr) => {
    imageContainerEl.innerHTML = "";

    let html = "";
    arr.forEach((e) => {
        html += `
            <div class="image-item" onclick="choseImage(this)">
                <img src="http://localhost:8080${e}" alt="">
            </div>
        `;
    });
    imageContainerEl.innerHTML = html;
};

// Chọn ảnh
const choseImage = (ele) => {
    // Xóa hết image được chọn trước đó
    const imageSelected = document.querySelector(".selected");
    if (imageSelected) {
        imageSelected.classList.remove(
            "border-3",
            "border-primary",
            "selected"
        );
    }

    // Highlight image vừa được click
    ele.classList.add("border-3", "border-primary", "selected");

    // Enable 2 nút "Chọn ảnh" và "Xóa ảnh"
    btnChoseImage.disabled = false;
    btnDeleteImage.disabled = false;
};

// Khi đóng modal chọn ảnh thì disabled 2 nút "Chọn ảnh" và "Xóa ảnh"
modalImageEl.addEventListener("hidden.bs.modal", () => {
    btnChoseImage.disabled = true;
    btnDeleteImage.disabled = true;
});

// Upload ảnh -> Đối tượng FormData để upload
avatarEl.addEventListener("change", async (e) => {
    try {
        // Lấy ra file upload
        let file = e.target.files[0];
        console.log(file);

        // Tạo form data
        let formData = new FormData();
        formData.append("file", file);

        // Gọi API
        let res = await axios.post(`${API_URL}/${id}/files`, formData);
        console.log(res);

        // Render lại ds image
        images.unshift(res.data);
        renderImage(images);
    } catch (error) {
        console.log(error);
    }
});
