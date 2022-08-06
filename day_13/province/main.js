// API lấy danh sách các tỉnh (province)
// GET : https://provinces.open-api.vn/api/p/

// Lấy danh sách quận huyện (district)
// GET : https://provinces.open-api.vn/api/p/${provinceCode}?depth=2

// Lấy danh sách xã phường
// GET : https://provinces.open-api.vn/api/d/${districtCode}?depth=2

const provinceEl = document.querySelector("#province");
const districtEl = document.querySelector("#district");

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

// Lấy danh sách quận huyện
const getDistrict = async (provinceCode) => {
    try {
        let res = await axios.get(`https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`);
        console.log(res);

        renderDistrict(res.data.districts)
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị quận huyện
const renderDistrict = arr => {
    districtEl.innerHTML = "";

    let html = "<option hidden>-- Chọn quận huyện</option>";
    arr.forEach(p => {
        html += `<option value=${p.code}>${p.name}</option>`
    });
    districtEl.innerHTML = html;
}

// Hiển thị tỉnh thành phố
const renderProvince = arr => {
    provinceEl.innerHTML = "";

    let html = "<option hidden>-- Chọn tỉnh thành phố</option>";
    arr.forEach(p => {
        html += `<option value=${p.code}>${p.name}</option>`
    });
    provinceEl.innerHTML = html;
}

// Lắng nghe sự kiện khi thay đổi tỉnh thành phố
provinceEl.addEventListener("change", () => {
    // Lấy ra code của tỉnh thành phố đang chọn
    let provinceCode = provinceEl.value;
    console.log(provinceCode)

    // Lấy ra danh sách quận huyện tương ứng -> hiển thị
    getDistrict(provinceCode);
})

getProvince();