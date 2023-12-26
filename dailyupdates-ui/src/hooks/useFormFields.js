import { useState } from "react"

export const useFormFields = (formState) => {
    const [formData, setFormData] = useState(formState);

    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    }

    return [
        formData,
        handleChange
    ]
}