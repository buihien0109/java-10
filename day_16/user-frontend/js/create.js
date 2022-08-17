const provinceEl = document.querySelector("#address");

toastr.success("Xin chào các bạn");
toastr.error("Có lỗi xảy ra");
toastr.warning("Cấm đụng vào");

// Lấy danh sách tỉnh thành phố
const getProvince = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res);

        renderProvince(res.data)
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị tỉnh thành phố
const renderProvince = arr => {
    provinceEl.innerHTML = "";

    let html = "<option hidden>-- Chọn tỉnh thành phố</option>";
    arr.forEach(p => {
        html += `<option value="${p.name}">${p.name}</option>`
    });
    provinceEl.innerHTML = html;
}

getProvince();