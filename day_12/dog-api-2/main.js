const breedListEl = document.getElementById("breed-list");

// https://dog.ceo/api/breeds/list/all
// Lấy danh sách giống loài và hiển thị
const getBreedList = async () => {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");

        renderBreedList(res.data.message);
    } catch (error) {
        console.log(error);
    }
};

const renderBreedList = (obj) => {
    let keys = Object.keys(obj);

    let html = "";
    keys.forEach((key) => {
        html += `<option value=${key}>${key}</option>`;
    });

    breedListEl.innerHTML = html;
};

getBreedList();
