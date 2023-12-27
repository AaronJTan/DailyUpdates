export default function ErrorText({children, className}) {
    return (
        <span className={`text-red-600 ${className}`}>
            {children}
        </span>
    );
}