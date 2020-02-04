let i = 1;

const helloWorld = () => {
    document.getElementById("buttonOutput").innerText = "Button Clicked " + i + " times. Nice.";
    i++;
};

const helloWorldAlert = () => {
    alert("Hello World!");
};

const showInput = () => {
  const input = document.getElementById("input").value;
  document.getElementById("output").innerText = input;
  alert("Found input: " + input);
  console.log(input);
};