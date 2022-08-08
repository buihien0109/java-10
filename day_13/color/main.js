const btnRandomColorName = document.querySelector("#btn-random-color-name");

btnRandomColorName.addEventListener("click", async () => {
    try {
        // Demo POST request
        // let res = await axios.post("http://localhost:8080/bmi-post", {
        //     height: 1.6,
        //     weight: 50
        // });
        // console.log(res)

        // renderBmi(res.data);

        let res = await axios.get("http://localhost:8080/random-color?type=4");
        console.log(res);

        document.body.style.backgroundColor = res.data;
    } catch (error) {
        console.log("Lỗi")
        console.log(error.response);
        alert(error.response.data.message);
    }
})

const renderBmi = (data) => {
    // Hiển thị BMI

    if (data <= 18.5) {
        // Hiển thị message
    } else if (data > 18.5 && data <= 29.9) {
        // Hiển thị message
    }
}

// console.log(btnRandomColorName.getAttribute("typeColor"));
console.log(btnRandomColorName.dataset.type);