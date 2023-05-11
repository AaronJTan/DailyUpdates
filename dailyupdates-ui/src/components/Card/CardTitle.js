export default function CardTitle({ className, children }) {
    return (
        <div className={`card-title ${className}`}>
            {children}
        </div>
    );
}