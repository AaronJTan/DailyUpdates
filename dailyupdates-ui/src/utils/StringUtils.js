export const StringUtils = {
    capitalizeFirstLetter: (string) => {
        return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
    },

    removeDashes: (string) => {
        return string.replace(/-/g, ' ');
    }
}