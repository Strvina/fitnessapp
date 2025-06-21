let allergyIndex = 0;
let itemIndex = 0;

function addAllergy() {
    const container = document.getElementById('allergy-container');

    const div = document.createElement('div');
    div.className = 'restriction-block';

    const hiddenType = document.createElement('input');
    hiddenType.type = 'hidden';
    hiddenType.name = `dietRestrictions[${allergyIndex}].restrictionType`;
    hiddenType.value = 'Alergija';

    const label = document.createElement('label');
    label.textContent = 'Stavka alergije';

    const input = document.createElement('input');
    input.type = 'text';
    input.name = `dietRestrictions[${allergyIndex}].itemName`;
    input.placeholder = 'Npr. Polen, Prašina...';

    div.appendChild(hiddenType);
    div.appendChild(label);
    div.appendChild(input);

    container.appendChild(div);
    allergyIndex++;
}

function addItemRestriction() {
    const container = document.getElementById('item-container');

    const div = document.createElement('div');
    div.className = 'restriction-block';

    const labelType = document.createElement('label');
    labelType.textContent = 'Tip restrikcije';

    const inputType = document.createElement('input');
    inputType.type = 'text';
    inputType.name = `dietRestrictions[${itemIndex}].restrictionType`;
    inputType.placeholder = 'Npr. Ishrana, Intolerancija...';

    div.appendChild(labelType);
    div.appendChild(inputType);

    container.appendChild(div);
    itemIndex++;
}
